package com.example.core.exception

class FieldErrorException(val errorFields: List<Pair<Int, Int>>): Exception()