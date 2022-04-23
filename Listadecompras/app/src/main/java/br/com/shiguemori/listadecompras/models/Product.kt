package br.com.shiguemori.listadecompras.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabela_produto")
data class Product(
    @PrimaryKey @ColumnInfo(name = "nome_produto") val name: String
)