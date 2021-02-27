package com.vsu.calculete_layer.layer;

import com.vsu.calculete_layer.layer.functions.legandr.NormMultiplier;
import com.vsu.calculete_layer.layer.functions.matrix.VariableB;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalculateLayer {

    //4 > i > 1
//    @SneakyThrows
//    public static double directTaskV(int n, double teta, double fi, double mX, double mY, double mZ) throws Exception {
//        double result = 0;
//        double temp = (1 / (4 * Math.PI * ConstantService.SIGMA_I.get(2)));
//        for (int i = 1; i < n; i++){
//            result += (temp * divRad(i) * mX * NormMultiplier.calculeteAttachedNormMultiplierLegandra(i, 1)  * NormMultiplier.Y(i, 1, teta, fi) * Math.cos(fi)) +
//                      (temp * divRad(i) * mY * NormMultiplier.calculeteAttachedNormMultiplierLegandra(i, -1) * NormMultiplier.Y(i, -1, teta, fi)) +
//                      (temp * divRad(i) * mZ * NormMultiplier.calculeteAttachedNormMultiplierLegandra(i, 0)  * NormMultiplier.Y(i, 0, teta, fi) * i);
//        }
//        return result;
//    }


    public static double directV(int n, double teta, double fi, double mX, double mY, double mZ, double R1, double rD) throws Exception {
        double result = 0;

        List<List<Double>> arrHim = new ArrayList<>();

        double temp = (1 / (4 * Math.PI * com.vsu.calculete_layer.layer.ConstantService.SIGMA_I.get(2)));
        for (int i = 1; i < n+1; i++){
            arrHim.add(Arrays.asList(
                    temp * divRad(i, rD) * mX / NormMultiplier.calculeteAttachedNormMultiplierLegandra(i, 1)  * NormMultiplier.Y(i, 1, teta, fi) * Math.cos(fi),
                    temp * divRad(i, rD) * mY / NormMultiplier.calculeteAttachedNormMultiplierLegandra(i, -1) * NormMultiplier.Y(i, -1, teta, fi),
                    temp * divRad(i, rD) * mZ / NormMultiplier.calculeteAttachedNormMultiplierLegandra(i, 0)  * NormMultiplier.Y(i, 0, teta, fi) * i
            ));
        }

        for (int i = 1; i < n; i++){
            for (int j = -1; j < 1; j++){
                int mArrH = j > 0 ? 0 : 1;
                if (j == 0){
                    mArrH = 2;
                }
                result += arrHim.get(i-1).get(mArrH) * (2 * i + 1) / (i * (VariableB.calculateMatrixB(i, 1).get(0).get(0))) *//(VariableB.calculateMatrixB(i, 2) //при 2 происходит деление на 0
                        Math.pow(ConstantService.R_I.get(0)/R1, i+1) * (NormMultiplier.Y(i, j, teta, fi) - NormMultiplier.Y(0, 0, teta, fi));
            }
        }

        return result;
    }


    private static double divRad(int i, double r_d){
        return Math.pow(r_d, i-1) / Math.pow(ConstantService.R_I.get(0), i+1);
    }
}
