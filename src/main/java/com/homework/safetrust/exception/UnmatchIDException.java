package com.homework.safetrust.exception;

/**
* 
* This is Exception class that handles Unmatched ID 
* between url ID and body Id in HTTP post request.
* 
*/
public class UnmatchIDException extends Exception{
    public UnmatchIDException(String message){
        super(message);
    }
}
