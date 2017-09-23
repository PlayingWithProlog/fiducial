:- use_module(library(socket)).

receive(Port) :-
    setup_call_cleanup(
        (   udp_socket(Socket),
            tcp_bind(Socket, Port)),
        hundred_packets(Socket),
        tcp_close_socket(Socket)
    ).

hundred_packets(S) :-
        between(1,100,_),
            udp_receive(S, Data, From, [as(codes)]),
            format('Got ~q from ~q~n', [Data, From]),
            fail.
hundred_packets(_).



