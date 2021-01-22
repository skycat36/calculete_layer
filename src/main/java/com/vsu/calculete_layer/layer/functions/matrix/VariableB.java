package com.vsu.calculete_layer.layer.functions.matrix;

import com.vsu.calculete_layer.converter.MatrixConverter;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.List;
import java.util.Objects;

public class VariableB {

    // i - начинается с 0
    public static List<List<Double>> calculateMatrixB(int L, int i) throws RuntimeException{

        RealMatrix result = MatrixUtils.createRealMatrix(Objects.requireNonNull(MatrixConverter.convert(VariableD.calculateMatrixD(L, i))));
        while (i > 0){
            i--;
            result = result.multiply(MatrixUtils.createRealMatrix(Objects.requireNonNull(MatrixConverter.convert(VariableD.calculateMatrixD(L, i)))));
        }

        return MatrixConverter.convert(result.getData());
    }

}
