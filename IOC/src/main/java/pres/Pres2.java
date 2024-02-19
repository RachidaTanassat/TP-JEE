package pres;

import dao.DaoImpl;
import dao.IDao;
import metier.IMetier;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Pres2 {
    public static void main(String[] args) throws Exception {
        Scanner scanner =new Scanner(new File("config.txt"));
        String daoClassName = scanner.nextLine();
        Class cDao = Class.forName(daoClassName);
        IDao dao = (IDao) cDao.getConstructor().newInstance();

        String metierClassName = scanner.nextLine();
        Class cmetier = Class.forName(metierClassName);
        IMetier metier = (IMetier) cmetier.getConstructor(IDao.class).newInstance(dao);

        //Method setDao = cmetier.getDeclaredMethod("setDao", IDao.class);
      //  setDao.invoke(metier, dao);

        System.out.println("res = "+ metier.calcul());

    }
}
