<a href="https://github.com/andres81/The-Self-Actualization-Project">The
Self-Actualization Project Documentation</a> © 2025
by <a href="https://www.andreschepers.nl">André Schepers</a> is licensed
under <a href="https://creativecommons.org/licenses/by/4.0/">CC BY
4.0</a><img src="https://mirrors.creativecommons.org/presskit/icons/cc.svg" alt="" style="max-width: 1em;max-height:1em;margin-left: .2em;"><img src="https://mirrors.creativecommons.org/presskit/icons/by.svg" alt="" style="max-width: 1em;max-height:1em;margin-left: .2em;">

# UC1 - OAuth2 Bearer Access and Refresh token creation

## Table of Contents

* Introduction
* Fully Dressed Format Use Case Description

## Introduction

## Fully Dressed Format Use Case Description

1. Create a new JWT token with the claim "__*type*__" set to "ACCESS_TOKEN". Set
   the required fields according to __*auth_sess_mgmnt_5*__ using the given
   __*client id*__.
2. Set the access token JWT its expiration time according to
   __*auth_sess_mgmnt_1*__.
3. Sign according to __*auth_sess_mgmnt_6*__.
4. Create a new JWT token with the claim "__*type*__" set to "REFRESH_TOKEN".
   Set the required fields according to __*auth_sess_mgmnt_5*__ using the given
   __*client id*__.
5. Set the refresh token JWT its expiration time according to
   __*auth_sess_mgmnt_2*__.
6. Sign according to __*auth_sess_mgmnt_6*__.
7. Return both tokens.
