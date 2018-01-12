package com.marre2retard.mar2retard.tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.marre2retard.mar2retard.models.Train;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gasmi on 29/12/2017.
 */

public class Utils {

    // method return train number from list
    static public String getTrainNumberFromList(List<Train> listTrain, String gareDepart, String gareArrivee, String heureDepart) {
        String trainNumber = "";
        if (listTrain != null) {
            for (Train train : listTrain) {
                if (train.getDepurtStation().equalsIgnoreCase(gareDepart) &&
                        train.getArrivalStation().equalsIgnoreCase(gareArrivee) &&
                        train.getDepartTime().equalsIgnoreCase(heureDepart)) {
                    trainNumber = train.getTrainNumber();
                }
            }
        }
        return trainNumber;

    }

    // method return list horaire train
    static public List<String> getListHoraireForTrainFromDepartAndArrival(List<Train> listTrain, String gareDepart, String gareArrivee) {
        List<String> listHoraire = new ArrayList<>();
        if (listTrain != null) {
            for (Train train : listTrain) {
                if (train.getDepurtStation().equalsIgnoreCase(gareDepart) &&
                        train.getArrivalStation().equalsIgnoreCase(gareArrivee)) {
                    listHoraire.add(train.getDepartTime());
                }
            }
        }

        return listHoraire;
    }

    // return name depart station with number train
    static public String getDepartStationWithNumberTrain(List<Train> listTrain, String numberTrain) {
        String nameDepartGare = "";
        if (listTrain != null) {
            for (Train train : listTrain) {
                if (train.getTrainNumber().equalsIgnoreCase(numberTrain)) {
                    nameDepartGare = train.getDepurtStation();
                }
            }
        }
        return nameDepartGare;
    }

    // return name arrival station with number train
    static public String getArrivalStationWithNumberTrain(List<Train> listTrain, String numberTrain) {
        String nameArrivalStation = "";
        if (listTrain != null) {
            for (Train train : listTrain) {
                if (train.getTrainNumber().equalsIgnoreCase(numberTrain)) {
                    nameArrivalStation = train.getArrivalStation();
                }
            }
        }
        return nameArrivalStation;
    }

    // return time depart train with number train
    static public String getHourDepartWithNumberTrain(List<Train> listTrain, String numberTrain) {
        String hourTrain = "";
        if (listTrain != null) {
            for (Train train : listTrain) {
                if (train.getTrainNumber().equalsIgnoreCase(numberTrain)) {
                    hourTrain = train.getDepartTime();
                }
            }
        }
        return hourTrain;
    }

    //return time Arrival train with number train
    static public String getHourArrivaltWithNumberTrain(List<Train> listTrain, String numberTrain) {
        String hourTrain = "";
        if (listTrain != null) {
            for (Train train : listTrain) {
                if (train.getTrainNumber().equalsIgnoreCase(numberTrain)) {
                    hourTrain = train.getArrivalTime();
                }
            }
        }
        return hourTrain;
    }

    static public int getIndexFromList(List<Train> listOfTrain, String nemaeGare) {
        int index = -1;

        for (Train train : listOfTrain) {
            if (train.getDepurtStation().equalsIgnoreCase(nemaeGare)) {
                index = listOfTrain.indexOf(train.getDepurtStation());
            }
        }
        return index;
    }

    // convert string base 64 to Bitmap
    public static Bitmap convert(String base64Str) throws IllegalArgumentException {
        byte[] decodedBytes = Base64.decode(
                base64Str.substring(base64Str.indexOf(",") + 1),
                Base64.DEFAULT
        );

        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    // convert Bitmap to string base 64
    public static String convert(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);

        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
    }

}
