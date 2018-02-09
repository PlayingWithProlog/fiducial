

graph_display(Ref) :-
    new(Ref, picture('Tangible Interface')),
    get(@display, width, W),
    get(@display, height, H),
    send(Ref, size, size(W, H)),
    send(Ref, move, point(0,0)),
    send(Ref, open).


add_stuff_demo(Ref) :-
    send(Ref, display, text('I have no ref'), point(200,200)),
    send(Ref, display, ellipse(50,50), point(40,40)), % no ref here either
    new(E, ellipse(60,60)),
    send(Ref, display, E, point(100,40)),
    free(E),  % it will never show up
    new(F, ellipse(80,40)),
    send(Ref, display, F),
    send(F, shadow, 13),
 %   send(F, selected, true),
    send(F, pen, 5),
    send(F, colour, blue),
    send(F, fill_pattern, colour(green)),
    get(F, area, A),
    debug(graph_display(demo), 'area of F is ~w', [A]),
    send(F, center, point(35, 190)),
    new(C, device),
    send(Ref, display, C),
    new(DE, ellipse(50,50)),
    new(DE2, ellipse(50,50)),
    send(C, display, DE),
    send(C, display, DE2),
    send(C, center, point(300,300)),
    send(DE2, colour, yellow),
    gensym(taco, Name),
    new(@Name, ellipse(160,200)),    % we can indirect names
    send(Ref, display, @Name).

