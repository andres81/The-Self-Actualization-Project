<a href="https://github.com/andres81/The-Self-Actualization-Project">The
Self-Actualization Project Documentation</a> © 2025
by <a href="https://www.andreschepers.nl">André Schepers</a> is licensed
under <a href="https://creativecommons.org/licenses/by/4.0/">CC BY
4.0</a><img src="https://mirrors.creativecommons.org/presskit/icons/cc.svg" alt="" style="max-width: 1em;max-height:1em;margin-left: .2em;"><img src="https://mirrors.creativecommons.org/presskit/icons/by.svg" alt="" style="max-width: 1em;max-height:1em;margin-left: .2em;">

# Use case UC1: Authentication

[Authentication](https://en.wikipedia.org/wiki/Authentication) is the starting
point for a user of using the system. Authentication in the context of this
system, also implies registering a user if not already done so.

A user can only log in with a provider. The current only available provider is
Google.

After login, an access and refresh token pair is returned. The refresh token is
returned as an http only, secure, Same-site cookie.

If during login with a provider, it turns out the user does not exist, the user
is created.

## Ubiquitous Language

| Term       | Description                                                                                                                 |
|------------|-----------------------------------------------------------------------------------------------------------------------------|
| User       | The user is the agent that will be authenticated. This can be a natural person or not (another software system)             |
| Client     | The client is the agent that does the request to authenticate on behalf of the user. Assumed is, that this is a webbrowser. |
| Webbrowser |                                                                                                                             |

## Use case description

1. The user, through the used client, makes a request to the backend to become
   authenticated