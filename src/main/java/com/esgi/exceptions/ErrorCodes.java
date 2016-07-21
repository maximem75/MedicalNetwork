package com.esgi.exceptions;

/**
 * Created by Arnaud on 21/07/2016.
 */
public class ErrorCodes {
    public static final String ERROR_WRONG_TOKEN = "This token is either non-existant in the database or it has expired. Try to reconnect to the application.";
    public static final String ERROR_MISSING_PARAMETERS = "You are missing one or several parameters required to satisfy your query.";
}
