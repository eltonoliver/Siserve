package com.app.siserve.siserve.adapter;

/**
 * Created by elton on 27/11/2016.
 */

public class Cliente {

    private String nomeCliente;
    private String cpf;
    private String tipoCli;
    private String endereco;
    private String telefone;

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = (nomeCliente == "null"|| "".equals(nomeCliente))?"Dados não cadastrados":nomeCliente;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = (cpf == "null" || "".equals(cpf))?"Dados não cadastrados":cpf;
    }

    public String getTipoCli() {
        return tipoCli;
    }

    public void setTipoCli(String tipoCli) {
        this.tipoCli = (tipoCli == "null" || "".equals(tipoCli))?"Dados não cadastrados":(tipoCli == "J")?"Jurídica":"Física";
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = (endereco == "null" || "".equals(endereco))?"Dados não cadastrados":endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = (telefone == "null" || "".equals(telefone))?"Dados não cadastrados":telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = (celular == "null" || "".equals(celular))?"Dados não cadastrados":celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = (email == "null" || "".equals(email))?"Dados não cadastrados":email;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = (nomeFantasia == "null" || "".equals(nomeFantasia))?"Dados não cadastrados":nomeFantasia;
    }

    private String celular;
    private String email;
    private String nomeFantasia;


}
