import { Component, EventEmitter, Input, Output } from '@angular/core';
import { BookResponse } from '../../../../services/models';
import { CommonModule } from '@angular/common';
import { RatingComponent } from '../rating/rating.component';

@Component({
  selector: 'app-book-card',
  standalone: true,
  imports: [CommonModule, RatingComponent],
  templateUrl: './book-card.component.html',
  styleUrl: './book-card.component.scss'
})
export class BookCardComponent {
  private _book: BookResponse = {};
  private _manage: boolean = false;
  private _bookCover: String | undefined;

  get manage(): boolean {
    return this._manage;
  }

  @Input()
  set manage(value: boolean){
    this._manage = value;
  }

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

  @Output() private share: EventEmitter<BookResponse> = new EventEmitter<BookResponse>();
  @Output() private archive: EventEmitter<BookResponse> = new EventEmitter<BookResponse>();
  @Output() private addToWaitingList: EventEmitter<BookResponse> = new EventEmitter<BookResponse>();
  @Output() private borrow: EventEmitter<BookResponse> = new EventEmitter<BookResponse>();
  @Output() private edit: EventEmitter<BookResponse> = new EventEmitter<BookResponse>();
  @Output() private details: EventEmitter<BookResponse> = new EventEmitter<BookResponse>();


  onShowDetails():void{
    this.details.emit(this._book);
  }
  onBorrow():void{
    this.borrow.emit(this._book);
  }
  onAddToWaitingList():void{
    this.addToWaitingList.emit(this._book);
  }
  onEdit():void{
    this.edit.emit(this._book);
  }
  onShare():void{
    this.share.emit(this._book);
  }
  onArchive():void{
    this.archive.emit(this._book);
  }

}
