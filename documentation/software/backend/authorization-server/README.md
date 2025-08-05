<a href="https://github.com/andres81/The-Self-Actualization-Project">The
Self-Actualization Project Documentation</a> © 2025
by <a href="https://www.andreschepers.nl">André Schepers</a> is licensed
under <a href="https://creativecommons.org/licenses/by/4.0/">CC BY
4.0</a><img src="https://mirrors.creativecommons.org/presskit/icons/cc.svg" alt="" style="max-width: 1em;max-height:1em;margin-left: .2em;"><img src="https://mirrors.creativecommons.org/presskit/icons/by.svg" alt="" style="max-width: 1em;max-height:1em;margin-left: .2em;">

# Authorization Server

## Table of Contents

* Introduction
* Software Design
* Documentation Structure
* Book references

## Introduction

<details>

The Authorization Server is a software component that can be used to
authenticate a user (human being or system), but also acts as an
[OAuth2 authorization server](https://www.rfc-editor.org/rfc/rfc6749).

Authentication itself is delegated to third parties, like Google or Facebook. A
third party will verify the identity and return user info. The only data point used will be the
subject information: A unique identifier, unique to the third party telling who
the user is. The authorization server will after authentication offer access
credentials that a client can use to access APIs offered. In the context of OAuth2,
these access credentials come in the form of two tokens: The access and refresh tokens.
The access token is used with every HTTP request to authorize the client with the
API being consumed.

The methodology for authentication/authorization used is
[OAuth2](https://en.wikipedia.org/wiki/OAuth). The reason to call this bounded
context the "authorization server" is to remain close to the naming used by
OAuth2.

</details>

##  Software Design

<details>

For the software design, several methodologies are used:

* [Unified Process](https://en.wikipedia.org/wiki/Unified_process) (UP)
* [Domain-Driven Design](https://en.wikipedia.org/wiki/Domain-driven_design)
  (DDD)
* [Systems engineering](https://en.wikipedia.org/wiki/Systems_engineering)
  MIT [style](https://learn-xpro.mit.edu/systems-engineering) using
  [Object Process Methodology](https://en.wikipedia.org/wiki/Object_Process_Methodology)

Following UP, use cases will be described first in the process of creating this
software. In parallel, domain rules will be written down as well as the vision of
this project.

</details>

## Documentation Structure

<details>

The structure of the documentation consists of separate directories, each with a
README.md for a quick introduction to the context of the documentation within
that directory, and subject specific Markdown files.

Following **UP**, the following artifacts are chosen:

* Use case descriptions
* Domain rules document
* Supplementary Specification document
* Vision document
* Glossary

The use cases can be found in the directory __*use-cases*__. The other artifacts
in the directory __*UP-artifacts*__.

</details>

## Book references

| Title                                                                                                        | Subjects covered                                                                                                                                          |
|--------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------|
| [Applying UML and Patterns](https://www.craiglarman.com/wiki/index.php?title=Book_Applying_UML_and_Patterns) | [Unified Process](https://en.wikipedia.org/wiki/Unified_process)<br/>[Unified Modeling Language](https://en.wikipedia.org/wiki/Unified_Modeling_Language) |
| [Implementing Domain-Driven Design](https://vaughnvernon.com/)                                                                                                         | [Domain-driven design](https://en.wikipedia.org/wiki/Domain-driven_design)                                                                                                                                                          |