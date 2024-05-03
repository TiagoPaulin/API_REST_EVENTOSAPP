package com.eventoapp.eventoapp.controllers;

import com.eventoapp.eventoapp.models.Convidado;
import com.eventoapp.eventoapp.models.Evento;
import com.eventoapp.eventoapp.repository.ConvidadoRepositoy;
import com.eventoapp.eventoapp.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class EventoController {
    @Autowired
    private EventoRepository er;
    @Autowired
    private ConvidadoRepositoy cr;

    @RequestMapping(value="/cadastrarEvento", method = RequestMethod.GET)
    public String form(){

        return "evento/formEvento";

    }

    @RequestMapping(value="/cadastrarEvento", method = RequestMethod.POST)
    public String form(@Valid Evento evento, BindingResult result, RedirectAttributes attributes){

        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os campos");
            return "redirect:/cadastrarEvento";
        }

        er.save(evento); // salva o evento no banco de dados

        attributes.addFlashAttribute("mensagem", "Evento Cadastrado com sucesso");

        return "redirect:/cadastrarEvento"; // redireciona o usuario

    }

    @RequestMapping("/eventos")
    public ModelAndView listaEventos(){

        Iterable<Evento> eventos = er.findAll();    // realiza a busca no banco de  dados

        ModelAndView mv = new ModelAndView("index"); // vai rendenizar a busca no banco
        mv.addObject("eventos", eventos); // passa a lista do banco para a view

        return mv;
    }

    @RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
    public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo){

        Evento evento = er.findByCodigo(codigo);    // encontra um evento especifico no banco

        ModelAndView mv = new ModelAndView("evento/detalhesEvento"); // vai rendenizar a busca no banco
        mv.addObject("evento", evento); // passa o evento do banco para a view

        Iterable<Convidado> convidados = cr.findByEvento(evento); // encontra os convidados cadastrados naquele evento

        mv.addObject("convidados", convidados);

        return mv;
    }

    @RequestMapping("/deletarEvento")
    public String deletarEvento(long codigo){

        Evento evento = er.findByCodigo(codigo);

        er.delete(evento);

        return "redirect:/eventos";
    }

    @RequestMapping(value = "/{codigo}", method = RequestMethod.POST)
    public String detalhesEventoPost(@PathVariable("codigo") long codigo, @Valid Convidado convidado, BindingResult result, RedirectAttributes attributes){

        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os campos");
            return "redirect:/{codigo}";
        }

        Evento evento = er.findByCodigo(codigo);    // encontra um evento especifico no banco

        convidado.setEvento(evento);
        cr.save(convidado);

        attributes.addFlashAttribute("mensagem", "Convidado adicionado com sucesso");

        return "redirect:/{codigo}";
    }

    @RequestMapping("/deletarConvidado")
    public String deletarConvidado(String rg){

        Convidado convidado = cr.findByRg(rg);

        Evento evento = convidado.getEvento();
        long codigo = evento.getCodigo();
        String strCodigo = "" + codigo;

        cr.delete(convidado);

        return "redirect:/" + strCodigo;
    }
}
