package com.eventoapp.eventoapp.controller;

import com.eventoapp.eventoapp.model.Convidado;
import com.eventoapp.eventoapp.model.Evento;
import com.eventoapp.eventoapp.repository.ConvidadoRepository;
import com.eventoapp.eventoapp.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class EventoController {

    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private ConvidadoRepository convidadoRepository;

    @PostMapping("/cadastrarEvento")
    public String form(@Valid Evento evento,BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem","verifique os campos!");
            return "redirect:/cadastrarEvento";
        }
        eventoRepository.save(evento);
        attributes.addFlashAttribute("mensagem","Evento cadastrado com sucesso!");
        return "redirect:/cadastrarEvento";
    }

    @PostMapping("/{codigo}")
    public String detalheEventoPost(@PathVariable("codigo") Long codigo, @Valid Convidado convidado, BindingResult result, RedirectAttributes attributes){

        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem","verifique os campos!");
            return "redirect:/{codigo}";
        }
        Evento evento = eventoRepository.findByCodigo(codigo);
        convidado.setEvento(evento);
        convidadoRepository.save(convidado);
        attributes.addFlashAttribute("mensagem","Convidado adicionado com sucesso!");
        return "redirect:/{codigo}";
    }

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

    @GetMapping("/{codigo}")
    public ModelAndView detalheEvento(@PathVariable("codigo") Long codigo){
        Evento evento = eventoRepository.findByCodigo(codigo);
        ModelAndView mv = new ModelAndView("evento/detalheEvento");
        mv.addObject("evento",evento);
        Iterable<Convidado> convidados = convidadoRepository.findByEvento(evento);
        mv.addObject("convidados",convidados);
        return mv;
    }

    @RequestMapping("/deletarEvento")
    public String deletarEvento(Long codigo){
        Evento eventoParaDeletar = eventoRepository.findByCodigo(codigo);
        eventoRepository.delete(eventoParaDeletar);
        return "redirect:/eventos";
    }
    @RequestMapping("/deletarConvidado")
    public String deletarConvidado(String rg){
        Convidado convidadoParaDeletar = convidadoRepository.findByRg(rg);
        convidadoRepository.delete(convidadoParaDeletar);
        Evento evento = convidadoParaDeletar.getEvento();
        Long codigoLong = evento.getCodigo();
        String codigo = "" + codigoLong;
        return "redirect:/" + codigo;
    }

}
