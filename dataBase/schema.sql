CREATE TABLE IF NOT EXISTS Usuario (
    Id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    senha TEXT NOT NULL,
    adm BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS Categoria (
    Id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    descricao TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS Objeto (
    Id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    descricao TEXT NOT NULL,
    categoria_id  INTEGER,
    FOREIGN KEY(categoria_id) REFERENCES Categoria (Id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS Movimentacao (
    Id INTEGER PRIMARY KEY AUTOINCREMENT,
    sentido TEXT NOT NULL,
    quantidade TEXT NOT NULL,
    objeto_id INTEGER,
    usuario_id INTEGER,
    FOREIGN KEY(objeto_id) REFERENCES Objeto (Id) ON DELETE SET NULL,
    FOREIGN KEY(usuario_id) REFERENCES Usuario (Id) ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS Estoque (
    Id INTEGER PRIMARY KEY AUTOINCREMENT,
    objeto_id INTEGER NOT NULL,
    quantidade INTEGER DEFAULT 0 NOT NULL,
    FOREIGN KEY(objeto_id) REFERENCES Objeto (Id) ON DELETE CASCADE
);

INSERT INTO Usuario (nome, senha, adm) VALUES ('admin', 'admin123', 1);