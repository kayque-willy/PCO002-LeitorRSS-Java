package br.com.pco002.service;

import br.com.pco002.model.Topico;
import br.com.pco002.repository.TopicoRepository;
import java.util.List;

public class TopicoService {

    private static final TopicoRepository topicoRepository = new TopicoRepository();

    public void save(Topico topico) {
        topicoRepository.save(topico);
    }

    public void remove(Topico topico) {
        topicoRepository.remove(Topico.class, topico.getId());
    }

    public Topico findById(Long id) {
        return (Topico) topicoRepository.findById(Topico.class, id);
    }

    public List<Topico> getAll() {
        return topicoRepository.getAll();
    }
}
