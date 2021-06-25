package com.eventoapp.eventoapp.controller;

import com.eventoapp.eventoapp.model.Evento;
import com.eventoapp.eventoapp.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class EventoController {

    @Autowired
    private EventoRepository eventoRepository;


    @GetMapping("/cadastrarEvento")
    public String form(){
        return "evento/formEvento";
    }

    @GetMapping("/eventos")
    public ModelAndView listaEventos(){
        ModelAndView mv = new ModelAndView("index");
        Iterable<Evento> eventos = eventoRepository.findAll();
        mv.addObject("eventos",eventos);
        return mv;
    }

    @PostMapping("/cadastrarEvento")
    public String form(Evento evento){
        eventoRepository.save(evento);
        return "redirect:/cadastrarEvento";
    }

    @GetMapping("/{codigo}")
    public ModelAndView detalheEvento(@PathVariable("codigo") Long codigo){
        Evento evento = eventoRepository.findByCodigo(codigo);
        ModelAndView mv = new ModelAndView("evento/detalheEvento");
        mv.addObject("evento",evento);
        return mv;
    }

}
