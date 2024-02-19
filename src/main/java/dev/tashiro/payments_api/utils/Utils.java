package dev.tashiro.payments_api.utils;

public class Utils {
  public static void RemoveEmptyParams(String param) {
    if (param != null && param.trim().isEmpty())
      param = null;
  }
}
