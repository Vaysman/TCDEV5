package com.netcracker.model;

public class InvalidBetSumException extends Exception{
    public String toString() {
        return "betSum < 0";
    }
}
