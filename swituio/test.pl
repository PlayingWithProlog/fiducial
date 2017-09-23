


receive(Port) :-
        udp_socket(S),
        tcp_bind(S, Port),
        listen(S),
        tcp_close_socket(S).

listen(S) :-
        between(1,100,_),
            udp_receive(S, Data, From, [as(codes)]),
            format('Got ~q from ~q~n', [Data, From]),
            format('~s~n', [Data]),
            fail.
listen(_).



% Got '#bundle\000\\000\\000\\000\\000\\000\\000\\000\\001\\000\\000\\000\(/tuio/2Dobj\000\,ss\000\source\000\\000\reacTIVision\000\\000\\000\\000\\000\\000\\000\\030\/tuio/2Dobj\000\,s\000\\000\alive\000\\000\\000\\000\\000\\000\\034\/tuio/2Dobj\000\,si\000\fseq\000\\000\\000\\000\ÿÿÿÿ' from ip(127,0,0,1):43168
