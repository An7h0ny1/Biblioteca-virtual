import { Component, Input } from '@angular/core';
import { BookResponse } from '../../../../services/models';

@Component({
  selector: 'app-book-card',
  standalone: true,
  imports: [],
  templateUrl: './book-card.component.html',
  styleUrl: './book-card.component.scss'
})
export class BookCardComponent {
  private _book: BookResponse = {};
  private _bookCover: String | undefined;

  get book(): BookResponse {
    return this._book;
  }

  @Input()
  set book(value: BookResponse){
    this._book = value;
  }

  get bookCover(): String | undefined {
    if(this._book.bookCover){
      return 'data:image/jpg;base64, ' + this._book.bookCover;
    }
    return 'https://images.freeimages.com/images/large-previews/360/guitar-1415372.jpg';
  }

}
