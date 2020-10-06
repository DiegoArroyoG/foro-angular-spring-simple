import {Comentario} from './comentario';
import {Usuario} from './usuario';

export class Tema {
  id: number;
  usuario: Usuario;
  comentario: Comentario[];
  fechaPublicacion: string;
  titulo: string;
  contenido: string;
  ranking: number;
  aprobado: boolean;
  constructor() {
  }
}
