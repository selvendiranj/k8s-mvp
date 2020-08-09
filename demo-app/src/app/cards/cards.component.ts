import { Component, OnInit } from '@angular/core';

import { CardService } from '../services/card.service';
import { Card } from '../models/card';

@Component({
  selector: 'app-cards',
  templateUrl: './cards.component.html',
  styleUrls: ['./cards.component.css']
})
export class CardsComponent implements OnInit {

  cards:any=[];

  constructor(private cardService: CardService) { }

  ngOnInit(): void {
	  this.getAllCards();
  }
  
  getAllCards() {
    this.cardService.getAllCards().subscribe(
      cards => {
        this.cards = cards;
        console.log('Get Cards : ' + cards);
      });
  }

}