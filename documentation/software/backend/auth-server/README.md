<a href="https://github.com/andres81/The-Self-Actualization-Project">The
Self-Actualization Project Documentation</a> © 2025
by <a href="https://www.andreschepers.nl">André Schepers</a> is licensed
under <a href="https://creativecommons.org/licenses/by/4.0/">CC BY
4.0</a><img src="https://mirrors.creativecommons.org/presskit/icons/cc.svg" alt="" style="max-width: 1em;max-height:1em;margin-left: .2em;"><img src="https://mirrors.creativecommons.org/presskit/icons/by.svg" alt="" style="max-width: 1em;max-height:1em;margin-left: .2em;">

# Auth Server

## Table of Contents

* Introduction
* Software Design
* Documentation Structure
* Book references

## Introduction

<details>

The Auth Server is
an [authentication server](https://en.wikipedia.org/wiki/Authentication_server).
The implementation, however, borrows heavily from the specification of the
[OAuth2 Authorization Server](https://www.rfc-editor.org/rfc/rfc6749#section-1.1).
The use cases implemented by this component, don't align with the use cases
offered by the OAuth2 specification. However, certain subsystems and concepts,
like
[Bearer tokens](https://www.rfc-editor.org/rfc/rfc6750),
[access and refresh tokens](https://www.rfc-editor.org/rfc/rfc6749#section-1.4)
, et cetera, are used.

Authentication itself is delegated to third parties, like Google or Facebook. A
third party will verify the identity and return user info. The only data point
used will be the
subject information: A unique identifier, unique to the third party telling who
the user is. For Google login, this is
the [Google ID token sub field](https://developers.google.com/identity/gsi/web/guides/verify-google-id-token)
(See:
__*"Key Point: Only use Google ID token sub field as identifier for the user
as it is unique among all Google Accounts and never reused."*__).

The Auth Server will after authentication return
[access and refresh tokens](https://www.rfc-editor.org/rfc/rfc6749#section-1.4),
according to the OAuth2 specification. The format for the tokens used, will be
[JWT](https://www.rfc-editor.org/rfc/rfc7519.html), to make the whole situation
stateless (access tokens are not stored in the database, but validated ad hoc
using
[PKI](https://en.wikipedia.org/wiki/Public_key_infrastructure)
(asymmetric) or
[MAC](https://en.wikipedia.org/wiki/Message_authentication_code) (symmetric)
).

</details>

## Software Design

<details>

For the software design, several methodologies are used:

* [Unified Process](https://en.wikipedia.org/wiki/Unified_process) (UP)
* [Domain-Driven Design](https://en.wikipedia.org/wiki/Domain-driven_design)
  (DDD)
* [Systems engineering](https://en.wikipedia.org/wiki/Systems_engineering)
  MIT [style](https://learn-xpro.mit.edu/systems-engineering) using
  [Object Process Methodology](https://en.wikipedia.org/wiki/Object_Process_Methodology)

Following UP, use cases will be described first in the process of creating this
software. In parallel, domain rules will be written down as well as the vision
of
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
| [Implementing Domain-Driven Design](https://vaughnvernon.com/)                                               | [Domain-driven design](https://en.wikipedia.org/wiki/Domain-driven_design)                                                                                |