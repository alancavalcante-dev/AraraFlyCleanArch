package br.com.alanpcavalcante.araraflyapi.domain.user;

public class Cpf {

    String cpf;

    public Cpf(String cpf) {
        validateExpressCpf(cpf);
        validateCpf(cpf);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }


    private void validateExpressCpf(String cpf) {
        if (!cpf.matches("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$")) {
            throw new IllegalArgumentException("Formato do cpf incorreto, valor esperado: xxx.xxx.xxx-xx");
        }
    }

    private void validateCpf(String cpf) {

        cpf = cpf.replace(".", "").replace("-", "");

        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            throw new IllegalArgumentException("");
        }

        try {
            int sum = 0;
            for (int i = 0; i < 9; i++) {
                sum += (cpf.charAt(i) - '0') * (10 - i);
            }
            int digit1 = 11 - (sum % 11);
            digit1 = (digit1 >= 10) ? 0 : digit1;

            sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += (cpf.charAt(i) - '0') * (11 - i);
            }
            int digit2 = 11 - (sum % 11);
            digit2 = (digit2 >= 10) ? 0 : digit2;

            boolean validate = (cpf.charAt(9) - '0') == digit1 && (cpf.charAt(10) - '0') == digit2;
            if (!validate) {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("CPF invalid!");
        }
    }


}
