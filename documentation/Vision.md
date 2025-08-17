<a href="https://github.com/andres81/The-Self-Actualization-Project">The
Self-Actualization Project Documentation</a> © 2025
by <a href="https://www.andreschepers.nl">André Schepers</a> is licensed
under <a href="https://creativecommons.org/licenses/by/4.0/">CC BY
4.0</a><img src="https://mirrors.creativecommons.org/presskit/icons/cc.svg" alt="" style="max-width: 1em;max-height:1em;margin-left: .2em;"><img src="https://mirrors.creativecommons.org/presskit/icons/by.svg" alt="" style="max-width: 1em;max-height:1em;margin-left: .2em;">

# The Self-Actualization Project Vision

## Table of Contents

* Introduction
* The Software Component

## Introduction

The Self-Actualization Project aims to provide the means to easily and as cheap
as possible to self-actualize as an individual. Among other parts in the future,
it consists of a software component. The software is used to offer a portal and
a set of tools on
the [World Wide Web](https://en.wikipedia.org/wiki/World_Wide_Web),
through which an individual can keep track of one's progress, but also do the
self-actualization itself.

An important pillar of self-actualization is education. A wonderful and very
effective way to educate people, is using software. But software is not enough.
Having a reference, a set of tutorials, and a single project focussing on the
concept of self-actualizing human beings, as cheap as possible, is necessary to
get the job done right.

## The Software Component

The software component consists of two parts.
A [Single Page Application](https://en.wikipedia.org/wiki/Single-page_application)
(SPA), the frontend, and
a [web service](https://en.wikipedia.org/wiki/Web_service),
the backend, that serves a [web API](https://en.wikipedia.org/wiki/Web_API).
Individuals can log in through the frontend. After logging in, the user can
start working on oneself.

The idea is to let the backend easily be extended, by allowing new additions to
easily be plugged in: The authentication is done centrally,
using [OAuth2](https://en.wikipedia.org/wiki/OAuth)
and [OpenID](https://en.wikipedia.org/wiki/OpenID), after which the user
principle is offered to the tool, with the tool being agnostic how the
principle was obtained/created/authenticated.

An example tool is the Dialogue Trainer Tool.
