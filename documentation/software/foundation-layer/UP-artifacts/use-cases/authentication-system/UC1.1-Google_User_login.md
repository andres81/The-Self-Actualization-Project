<a href="https://github.com/andres81/The-Self-Actualization-Project">The
Self-Actualization Project Documentation</a> © 2025
by <a href="https://www.andreschepers.nl">André Schepers</a> is licensed
under <a href="https://creativecommons.org/licenses/by/4.0/">CC BY
4.0</a><img src="https://mirrors.creativecommons.org/presskit/icons/cc.svg" alt="" style="max-width: 1em;max-height:1em;margin-left: .2em;"><img src="https://mirrors.creativecommons.org/presskit/icons/by.svg" alt="" style="max-width: 1em;max-height:1em;margin-left: .2em;">

# UC1.1 - Google User login

## Table of Contents

* Introduction
* Fully Dressed Format Use Case Description

## Introduction

## Fully Dressed Format Use Case Description

<details>

1. User starts the [Google login](https://developers.google.com/identity/siwg)
   by clicking
   the [Google Login button](https://developers.google.com/identity/gsi/web/guides/get-google-api-clientid)
   on the webpage.
2. The button click redirects the user to Google, where the user can log in.
3. After login is successful, Google will redirect back to the SAPS web page.
4. The Google Login library picks up the redirect with information provided by
   Google, and makes the Google Login information available to the SPA.
5. The SPA will make a login request to the Web server with the Google
   information.
6. The Web Server will verify the Google information provided by making a call
   to the Google backend
7. The Web Server will take the "__*sub*__" field from the
   [Google ID Token](https://developers.google.com/identity/gsi/web/guides/verify-google-id-token)
   and returns it from this flow.

</details>
