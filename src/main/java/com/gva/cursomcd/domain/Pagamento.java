package com.gva.cursomcd.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.gva.cursomcd.enums.EstadoPagamento;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type" )
public abstract class Pagamento implements Serializable{

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @EqualsAndHashCode.Include
    @Id
    private Integer id;

    private Integer estado;

    @JsonIgnore
    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "PEDIDO_ID")
    @MapsId
    private Pedido pedido;

    public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) {
		super();
		this.id = id;
		this.estado = (estado == null ? null : estado.getCod());
		this.pedido = pedido;
	}

    public EstadoPagamento getEstado() {
		return EstadoPagamento.toEnum(estado);
	}

	public void setEstado(EstadoPagamento estado) {
		this.estado = estado.getCod();
	}

}
