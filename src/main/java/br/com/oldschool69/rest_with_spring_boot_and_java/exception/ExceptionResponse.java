package br.com.oldschool69.rest_with_spring_boot_and_java.exception;

import java.util.Date;

public record ExceptionResponse(Date timestamp, String message, String details) {}
