package org.example.smhelloworld;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class HelloWorldCommand {

    @ShellMethod("Add two integers together.")
    public int add(int a, int b) {
        return a + b;
    }

}
