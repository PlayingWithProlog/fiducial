:- module(testosc, [osc/1]).

:- use_module(library(plosc)).

:- dynamic server/2.

ack(_,_) :- writeln(ack_received).

echo(P,A) :- writeln(msg(P,A)).
echo(Sender,Time,P,A) :-
	format('from ~w at ~w: ~w.\n',[Sender,Time,msg(P,A)]),
	(	Time=osc_immed -> osc_send(Sender,'/ack',[])
	;	osc_time_ts(T0,Time) -> osc_send(Sender,'/ack',[],T0+1)
	).

update_tangible(Dict) :-
	debug(tuio(update), '~w', [Dict]).

tangible_entered(int(Session)) :-
	debug(tuio(enter), '~w entered', [Session]).
tangible_left(int(Session)) :-
	debug(tuio(left), '~w left', [Session]).

:- initialization thread_initialization(nb_setval(present_tangibles, [])).
% /tuio/2Dobj set s i x y a X Y A m r
echo_2Dobj('/tuio/2Dobj', [string(set) | Args]) :-
	debug(tuio(set), 'set ~w', [Args]),
	Args = [int(Sess),
		int(Class),
		float(X),    % 0-1
		float(Y),    % 0-1
		float(Angle),
		float(XVel),
		 float(YVel),
		 float(AngleVel),
		 float(Accel),
		 float(RotAccel)],
	update_tangible(set{
		  session: Sess,
		  class: Class,
		  pos: pt{x:X, y:Y, angle:Angle},
		  velocity: pt{x:XVel, y:YVel, angle:AngleVel},
		  accel: Accel,
		  rot_accel: RotAccel
	      }).
echo_2Dobj('/tuio/2Dobj', [string(alive) | Args]) :-
	debug(tuio(alive), 'alive ~w', [Args]),
	nb_getval(present_tangibles, CurrentTangibles),
	list_to_ord_set(Args, NewTangibles),
	ord_subtract(NewTangibles, CurrentTangibles, Entered),
	ord_subtract(CurrentTangibles, NewTangibles, Left),
	maplist(tangible_entered, Entered),
	maplist(tangible_left, Left),
	nb_setval(present_tangibles, NewTangibles).

echo_2Dobj('/tuio/2Dobj', [X | Args]) :-
	X \= string(set),
	debug(tuio(spam), '~w', [[X | Args]]).
echo_2Dobj(P, A) :-
	debug(tuio(unknown), '~w ~w', [P, A]).

forward(_,[string(Host),int(Port),string(Msg)|Args]) :-
	osc_mk_address(Host,Port,Addr),
	osc_send(Addr,Msg,Args).

sched_at(_,[double(Delay),string(Host),int(Port),string(Msg)|Args]) :-
	get_time(Now), Time is Now+Delay,
	osc_mk_address(Host,Port,Addr),
	osc_send(Addr,Msg,Args,Time).

osc(init(Port)) :- osc_mk_server(Port,S),
	osc_mk_address(localhost,Port,P),
	osc_add_handler_x(S, '/echox', any, echo),
	osc_add_handler(S, '/echo',  any, echo),
	osc_add_handler(S, '/fwd',   any, forward),
%	osc_add_handler(S, '/after', any, sched_in),
	osc_add_handler(S, '/ack',   any, ack),
	osc_add_handler(S, '/tuio/2Dobj', any, echo_2Dobj),
	assert(server(S,P)).

osc(start) :- server(S,_), osc_start_server(S), at_halt(osc(stop)).
osc(stop)  :- server(S,_), osc_stop_server(S).
osc(run)   :- server(S,_), osc_run_server(S).

osc(send(M,A)) :- server(_,P), osc_send(P,M,A).
osc(send(M,A,T)) :- server(_,P), osc_send(P,M,A,T).
osc(send_from(M,A,T)) :- server(S,P), osc_send_from(S,P,M,A,T).

run(Port) :- osc(init(Port)),
	nl,
	writeln('Commands:'), nl,
	writeln('   osc(start)  to start the server in a new thread.'),
	writeln('   osc(stop)   to stop the server thread.'),
	writeln('   osc(run)    run the server synchronously in the current thread.'),
	writeln('   osc(send(Path,Args)) send message with Path and Args.'),
	writeln('   osc(send(Path,Args,Time)) send timestamped message with Path and Args.'),
	nl,
	writeln('OSC messages:'), nl,
	writeln('   /echo <<args>>'),
	writeln('         write messages and arguments.'),
	writeln('   /echox <<args>>'),
	writeln('         write messages and arguments with sender and timestamp.'),
	writeln('   /fwd   s<host> i<port> s<path> <<args>>'),
	writeln('         forward message to given server.'),
	writeln('   /after d<delay> s<host> i<port> s<path> <<args>>'),
	writeln('         forward message after delay.'),
	writeln('   /plosc/stop'),
	writeln('         stop the synchronous server.'),
	nl.
