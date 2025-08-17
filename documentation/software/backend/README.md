<a href="https://github.com/andres81/The-Self-Actualization-Project">The
Self-Actualization Project Documentation</a> © 2025
by <a href="https://www.andreschepers.nl">André Schepers</a> is licensed
under <a href="https://creativecommons.org/licenses/by/4.0/">CC BY
4.0</a><img src="https://mirrors.creativecommons.org/presskit/icons/cc.svg" alt="" style="max-width: 1em;max-height:1em;margin-left: .2em;"><img src="https://mirrors.creativecommons.org/presskit/icons/by.svg" alt="" style="max-width: 1em;max-height:1em;margin-left: .2em;">

# The Self-Actualization Project Backend

## Table of Contents

* Introduction
* Architecture Overview

## Introduction

The backend component is used to store state in the database of users, handle
authentication and authorization and implement domain logic regarding tools
offered by the software and the base project itself.

Using the concept from Domain-driven Design (DDD), the bounded context, the
backend project is split up in several bounded contexts that work together. This
gives a modular setup of the backend system. Separate subsystems forming the
full backend system together.

There is often confusion that leads to a lot of discussion regarding two
concepts:

* Software Design
* Software Architecture

This causes a lot of energy being spent on talking about concepts and how they
are used in the IT industry, while that energy is better spent on building
useful software. Therefore, the concepts are clearly described how they are
regarded within the context of this project, using the two types of architecture
described in System Engineering:

* Formal architecture: Software Architecture
* [Functional architecture](https://mbse.solutions/functional-architecture-using-sysml/)
  (and [here](https://sebokwiki.org/wiki/Functional_Architecture)): Software
  Design

The general idea is to be pragmatic, not puristic regarding one or a view
methodologies. Instead, the advice given
in [Applying UML and Patterns](https://www.craiglarman.com/wiki/index.php?title=Book_Applying_UML_and_Patterns)
is followed: Diagrams give visual overviews and help understand what we are
building.

## Architecture Overview

Bounded contexts:

* Authorization Server
* User management
* Admin system

### Functional architecture: Software Design

Software design can be done in many ways. Within this project, the choice was
made to use Unified Process (UP) and Domain-Driven Design (DDD). With DDD
separate components can be described as Bounded Contexts, while with UP the
usage of the system, and subcomponents can be easily described and visualized.
Furthermore, using Object Process Methodology its Object Process Model, another
great view can be created of the functionality desired of the system.

