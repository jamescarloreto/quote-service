# Terminology

- Symbol: The identifier for something that can be traded.
- Bid: The price a trader offers to buy a symbol.
- Ask: The price a trader offers to sell a symbol.
- Quote: The highest bid and lowest ask.

# Instructions

Please write a web app that reads the latest quote for a symbol from an in-memory database.

The app should be written in Java and Spring Boot is preferred.
Please contact us if you'd like to use a different implementation.

## Database

You'll need to retrieve the latest quotes from the in-memory H2 database.

This database initialized by `init.sql` in the `resources` folder.
`application.properties` already has the Spring Boot configuration to connect to this database.

The database only has one table, `QUOTE`, which stores
each day's quotes for the symbols, e.g.:

| SYMBOL | Day        | BID  | ASK  |
| ------ | ---------- | ---- | ---- |
| MSFT   | 2020-01-01 | 1.23 | 4.56 |
| ...    | ...        | ...  | ...  |

The table is keyed by the combination of `SYMBOL` and `DAY`.

## API

The web app has one endpoint accepts any symbol (e.g., `MSFT`)
and returns the most recent quote for that symbol.

For example:

    GET /symbols/MSFT/quotes/latest
    {"bid": 1.23, "ask": 4.56}

The symbol must be _at least_ 2 characters and _at most_ 6. The service must validate this.

If the symbol is valid and data exists,
the endpoint should return a 200 OK response code
and a JSON body with `bid` and `ask` fields.

If the symbol is valid, but no data exists,
the endpoint should return a 404 Not Found response code.

If the symbol is not valid,
the endpoint should return a 400 Bad Request response code.

## Bonus Objectives

To distinguish themselves, a senior developer can tackle some of bonus requirements:

- A test suite.
- Caching.
- Authentication.
- An additional endpoint to retrieve the symbol with the highest ask for a given day.

## Submission

Please email us a zip or tar of the source code with instructions for how to run it.

If you're using a version control system, please include its data (e.g., the `.git` folder).
