<a href="https://github.com/andres81/The-Self-Actualization-Project">The
Self-Actualization Project Documentation</a> © 2025
by <a href="https://www.andreschepers.nl">André Schepers</a> is licensed
under <a href="https://creativecommons.org/licenses/by/4.0/">CC BY
4.0</a><img src="https://mirrors.creativecommons.org/presskit/icons/cc.svg" alt="" style="max-width: 1em;max-height:1em;margin-left: .2em;"><img src="https://mirrors.creativecommons.org/presskit/icons/by.svg" alt="" style="max-width: 1em;max-height:1em;margin-left: .2em;">

# UC1.0 - User login

## Table of Contents

* Introduction
* Fully Dressed Format Use Case Description

## Introduction

## Fully Dressed Format Use Case Description

1. User chooses to log in on the website of the project using either the
   [Google Login flow](UC1.1-Google_User_login.md)
   or the
   [Facebook Login flow](UC1.2-Facebook_User_login.md).
2. Using the returned subject information from the third party login provider,
   the application retrieves the user information from the database.
3. Using the user information
   [OAuth2 Bearer access and refresh tokens are created](UC1.3-OAuth2-Bearer-Access-Refresh-Tokens-creation.md)
4. The application
   [creates a proper HTTP response](UC1.4-Create-proper-authenticated-login-HTTP-response.md)
   (Cookie creation and authentication material).
