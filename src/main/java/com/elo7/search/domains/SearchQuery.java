package com.elo7.search.domains;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class SearchQuery {

    @NotNull(message = "searchQuery.q.notNull")
    private String q;

}
