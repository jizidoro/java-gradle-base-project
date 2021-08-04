/*
 * Este arquivo pertence a Petrobras e nao pode ser utilizado fora desta empresa
 * sem previa autorizacao.
 * ----------------------------------
 * Esta classe segue o padrao PE-2T0-00250
 */
package com.comrades.core.log;

public enum FuncionalidadeLogEnum implements PersistentEnum {

    FLUIDO(1L),
    ACESSO(2L),
	TIPOEQUIPAMENTO(3L),
	PLANTA(4L),
	DISTRITO(5L),
	UNIDADEPROCESSO(6L),
	CATEGORIAEQUIPAMENTO(7L),
	FABRICANTE(8L),
	PROJETOFABRICACAO(9L),
	EQUIPAMENTO(10L),
    COMPONENT(11L),
    USUARIO(12L),
    PERFIL_USUARIO(13L),
    MECANISMO_DANO(14L),
    MARCADOR_EQUIPAMENTO(15L),
    METODO_CONTROLE(16L),
    REVESTIMENTO(17L),
    CONCLUSAO_INSPECAO(18L),
    MOTIVO_INSPECAO(19L),
    MATERIAL_ESPECIFICO(20L),
    TIPO_GLOBAL_COMPONENTE(21L),
    TIPO_COMPONENTE(22L),
    ESPECIFICACAO_TUBULACAO(23L),
    BITOLA_TUBO(24L),
    COMPONENTE(25L),
    PONTOENSAIO(26L),
    RELATORIO_INSPECAO(27L)
    ;

    private final Long codigo;

    FuncionalidadeLogEnum(Long codigo) {
        this.codigo = codigo;
    }

    @Override
    public final Long getCodigo() {
        return codigo;
    }
}
