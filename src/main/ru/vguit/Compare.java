package ru.vguit;

import java.util.Arrays;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by aleksejpleskanev on 03.12.16.
 * <p>
 * Класс-утилита для выполнения математических расчетов,
 * связанных для работы нейронной сети
 */
public final class Compare {

  private final static Logger LOG = LoggerFactory.getLogger(Compare.class);

  /**
   * Выполнение операции перемножения матрицы и вектора
   *
   * @param vector вектор, на который будет умножаться матрица
   * @param matrix матрица, на которую будет умножаться вектор
   *
   * @return вектор - результат вычисления произведения матрицы и вектора
   */
  public static double[] multipleMartixVector(double[] vector, double[][] matrix) {
    if (Objects.isNull(vector)) {
      throw new IllegalArgumentException("argument \"vector\" is NULL");
    }
    if (Objects.isNull(matrix)) {
      throw new IllegalArgumentException("argument \"matrix\" is NULL");
    }

    if (vector.length != matrix[0].length) {
      throw new IllegalArgumentException(
          "Число столбцов в матрице не совпадает с числом строк в векторе, перемножение невозможно");
    }

    if (LOG.isDebugEnabled()) {
      LOG.debug("Значения вектора:");
      Arrays.stream(vector).forEach(item -> LOG.debug(String.valueOf(item)));

      LOG.debug("Значения матрицы");

      StringBuilder format = new StringBuilder();

      for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix[i].length; j++) {
          format.append(String.valueOf(matrix[i][j]) + " ");
        }
        LOG.debug(format.toString());
        format.setLength(0);
      }
    }

    double[] respVector = new double[vector.length];

    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < vector.length; j++) {
        respVector[j] += matrix[i][i + j] * vector[j];
        LOG.debug("Значение {} рассчитанного вектора: {}", i, respVector[j]);
      }
    }

    return respVector;
  }

  /**
   * Функция качества
   *
   * @param x параметр в функции качества
   *
   * @return значение функции качества
   */
  public static double compareQualityFunc(double x) {
    return 1 / (1 + Math.pow(Math.E, -x));
  }

}
