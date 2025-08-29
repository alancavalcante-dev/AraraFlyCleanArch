package br.com.alanpcavalcante.araraflyapi.application.usecases.project;


import org.springframework.data.domain.Sort;

import java.util.List;

public class CustomPage<T> {
    private final List<T> content;
    private final int pageNumber; // Número da página atual (base 0)
    private final int pageSize;   // Tamanho da página
    private final long totalElements; // Total de elementos em todas as páginas
    private final int totalPages;     // Total de páginas
    private final Object sort;

    public CustomPage(List<T> content, int pageNumber, int pageSize, long totalElements, int totalPages, Object sort) {
        this.content = content;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.sort = sort;
    }

    // --- Getters Básicos ---

    public List<T> getContent() {
        return content;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public Object getSort() {
        return sort;
    }

    // --- Métodos de Conveniência ---

    /**
     * Retorna se a página atual é a primeira.
     * @return true se for a primeira página.
     */
    public boolean isFirst() {
        return pageNumber == 0;
    }

    /**
     * Retorna se a página atual é a última.
     * @return true se for a última página.
     */
    public boolean isLast() {
        return pageNumber == totalPages - 1;
    }

    /**
     * Retorna se existe uma próxima página.
     * @return true se houver uma próxima página.
     */
    public boolean hasNext() {
        return pageNumber < totalPages - 1;
    }

    /**
     * Retorna se existe uma página anterior.
     * @return true se houver uma página anterior.
     */
    public boolean hasPrevious() {
        return pageNumber > 0;
    }

    /**
     * Retorna se o conteúdo da página está vazio.
     * @return true se a lista de conteúdo estiver vazia.
     */
    public boolean isEmpty() {
        return content.isEmpty();
    }
}
