This simple movie API app is made for practicing DDD(Domain Driven Design) and TDD(Test Driven Development).

This app has 6 domains: member, movie, category, actor, review and grade. each domain has the following responsibilities,
I will explain just member and movie domains simply, 

the member domain is related with user informations. this domain has responsibilities of sign up, sign in, buying movie.
and when users sign up this app, e-mail send event occurs. this event handler is seperated from the member domain.

the movie domain is related with movie informations. this domain will be shown users their informations about grades, actors and etc.

I made this app by TDD process that designing the domain, writing tests, and writing code that passes the tests.