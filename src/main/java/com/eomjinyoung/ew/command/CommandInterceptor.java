package com.eomjinyoung.ew.command;

public interface CommandInterceptor {
  boolean preHandle(String input, Command command);
}
