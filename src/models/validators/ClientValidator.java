package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.Client;
import utils.DBUtil;
public class ClientValidator {

    public static List<String> validate(Client c, Boolean num_duplicate_check_flag, Boolean password_check_flag) {
        List<String> errors = new ArrayList<String>();

        String num_error = _validateNum(c.getNum(), num_duplicate_check_flag);
        if (!num_error.equals("")) {
            errors.add(num_error);
        }
        String name_error = _validateName(c.getName());
        if (!name_error.equals("")) {
            errors.add(name_error);
        }

        return errors;
    }

    private static String _validateNum(String num, Boolean num_duplicate_check_flag) {

        if (num == null || num.equals("")) {
            return "取引先企業コードを入力してください。";

        }
        if (num_duplicate_check_flag) {
            EntityManager em = DBUtil.createEntityManager();
            long clients_count = (long) em.createNamedQuery("checkRegisteredNum", Long.class)
                    .setParameter("num", num)
                    .getSingleResult();
            em.close();
            if (clients_count > 0) {
                return "入力された取引先コードの情報はすでに存在しています。";

            }
        }
        return "";

    }

    private static String _validateName(String name) {
        if (name == null || name.equals("")) {
            return "企業名を入力してください。";
        }
        return "";
    }
}
