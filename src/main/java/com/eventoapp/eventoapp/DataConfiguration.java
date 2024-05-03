package com.eventoapp.eventoapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Configuration // classe de configuração do banco de dados
public class DataConfiguration {

    @Bean // Bean de conexação com o banco de dados
    public DataSource dataSource () {

        DriverManagerDataSource datasource = new DriverManagerDataSource();

        datasource.setDriverClassName("com.mysql.jdbc.Driver");
        datasource.setUrl("jdbc:mysql://localhost:3306/nome_do_banco"); //url do banco com o nome do banco
        datasource.setUsername("root");                              // root do mysql
        datasource.setPassword("senha_do_root");                             // senha do root

        return datasource;
    }

    @Bean // Bean de configuração do Hibernate
    public JpaVendorAdapter jpaVendorAdapter () {

        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();

        adapter.setDatabase(Database.MYSQL);  // definindo a database utilizada
        adapter.setShowSql(true); // mostra todas as etapas quando um dado é inserido ou deletado
        adapter.setGenerateDdl(true); // permite que o Hibernate crie as tabelas do banco automaticamente
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
        adapter.setPrepareConnection(true); // Hibernate estabelece conexão automaticamente

        return adapter;
    }

}
