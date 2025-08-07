<a href="https://github.com/andres81/The-Self-Actualization-Project">The
Self-Actualization Project Documentation</a> © 2025
by <a href="https://www.andreschepers.nl">André Schepers</a> is licensed
under <a href="https://creativecommons.org/licenses/by/4.0/">CC BY
4.0</a><img src="https://mirrors.creativecommons.org/presskit/icons/cc.svg" alt="" style="max-width: 1em;max-height:1em;margin-left: .2em;"><img src="https://mirrors.creativecommons.org/presskit/icons/by.svg" alt="" style="max-width: 1em;max-height:1em;margin-left: .2em;">

# Auth Server Use Cases

This document will give an overview of use cases implemented by this software
component. The use cases will be given in brief format. Dedicated files are
given for fully dressed format use cases.

## Use Cases

### UC1 - User authentication

Given a subject id, a user is retrieved from the database, or otherwise created.
After the user is retrieved or created and persisted, the user is given a pair
of tokens consisting of an access and a refresh token.

### UC2 - User registration

### UC3 - Google Authentication

Google login consists of a user authenticating with Google, after which Google
will send the
[Google ID token](https://developers.google.com/identity/gsi/web/guides/verify-google-id-token)
back. The backend Google Login starts upon receiving the Google ID token.

### UC4 - Exchange refresh token for a new access token



### UC5 - Logout

