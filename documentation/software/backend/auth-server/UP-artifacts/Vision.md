<a href="https://github.com/andres81/The-Self-Actualization-Project">The
Self-Actualization Project Documentation</a> © 2025
by <a href="https://www.andreschepers.nl">André Schepers</a> is licensed
under <a href="https://creativecommons.org/licenses/by/4.0/">CC BY
4.0</a><img src="https://mirrors.creativecommons.org/presskit/icons/cc.svg" alt="" style="max-width: 1em;max-height:1em;margin-left: .2em;"><img src="https://mirrors.creativecommons.org/presskit/icons/by.svg" alt="" style="max-width: 1em;max-height:1em;margin-left: .2em;">

# Auth Server Vision

In short, the vision is to have a separate software component responsible for
handling authentication and authorization. Keeping systems separate from each
other, decreases complexity, increases maintainability and evolution of the
system. Keeping complex large subjects like authentication and authorization out
of the scope of other systems, keeps other systems also light and easy to
understand.

The keywords of this system should be: Stateless, flexible, autonomous and
secure.

The idea is, using key technologies and specifications, together with a
pragmatic approach, to have a DDD bounded context that is easily to understand,
maintain, evolve and extend. But at the same time offers industrial grade
security to the web server application it is part of and used within its
context.

Key technological aspects are: Hexagonal application architecture, fully
autonomous Domain-Driven Design Bounded Context, stateless auth material in the
form of Json Web Tokens, functionality loosely coupled by having authorization
decoupled from authentication, and both decoupled from the Auth Server itself:

* Authentication itself is delegated to third parties like Google
* Authorization is done by other bounded contexts irrespective of the Auth
  Server as JWT tech supports this (Claims inside the JWT are the authorization
  material)
