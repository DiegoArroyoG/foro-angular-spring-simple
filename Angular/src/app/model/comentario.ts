import {Usuario} from './usuario';

export class Comentario{
  id: number;
  usuario: Usuario;
  comentarios: Comentario[];
  fecha: string;
  contenido: string;
  ranking: number;
  aprobado: boolean;
  constructor() {
  }
}
