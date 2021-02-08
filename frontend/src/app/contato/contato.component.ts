import { Component, OnInit } from '@angular/core';
import { ContatoService } from '../contato.service';
import { Contato } from './Contato';

@Component({
  selector: 'app-contato',
  templateUrl: './contato.component.html',
  styleUrls: ['./contato.component.css']
})
export class ContatoComponent implements OnInit {

  constructor(private service: ContatoService) { }

  ngOnInit(): void {
    const c: Contato = new Contato();
    c.nome = 'José';
    c.email = 'jose@email.com'
    c.favorito = false;

    this.service.save(c).subscribe(response => console.log(response))
  }

}
