package Uno.Auxiliares;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.function.Predicate;

public class ArrayListBom<E> extends ArrayList<E> {
    public E ultimo() {
        return this.isEmpty() ? null : this.get(this.size() - 1);
    }

    public E aleatorio() {
        return this.size() == 0 ? null : this.get(new Random().nextInt(this.size()));
    }

    public E removerAleatorio(){
        return remover(aleatorio());
    }

    public ArrayListBom<E> filtrar(Predicate<E> predicado){
        ArrayListBom<E> filtrados = new ArrayListBom<>();
        for(E item : this)
            if(predicado.test(item))
                filtrados.add(item);
        return filtrados;
    }

    public E procurar(Predicate<E> predicado){
        for(E item : this)
            if(predicado.test(item))
                return item;
        return null;
    }

    public ArrayListBom<E> embaralhar(){
        Collections.shuffle(this);
        return this;
    }

    public ArrayListBom<E> novo(E elemento){
        this.add(elemento);
        return this;
    }

    public E remover(E item){
        if(this.remove(item))
            return item;
        return null;
    }

    public E removerUltimo(){
        return remover(ultimo());
    }

    public ArrayListBom<E> moverTudo(ArrayListBom<E> destino){
        while(!this.isEmpty())
            destino.add(this.remove(0));
        return destino;
    }

    public E primeiro(){
        return this.isEmpty() ? null : this.get(0);
    }

    public ArrayListBom<E> ultimos(int qtd){
        ArrayListBom<E> lista = new ArrayListBom<>();
        for(int i = Math.max(0, this.size()-qtd); i < this.size(); i++)
            lista.add(this.get(i));
        return lista;
    }
}
