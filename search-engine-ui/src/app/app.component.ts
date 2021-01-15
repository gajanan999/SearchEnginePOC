import {AfterViewInit, OnInit, Component, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import { DataService } from './data.service';
import {FormControl} from '@angular/forms';
import {Observable} from 'rxjs';
import { startWith } from 'rxjs/operators';
import {map} from 'rxjs/operators';
import {filter} from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, AfterViewInit{
  title = 'search-engine-ui';

  suggestionList:string[]
  typedKeyword = ''

  myControl: FormControl = new FormControl();

  
  @ViewChild(MatPaginator, {static: false}) paginator: MatPaginator;
  //@ViewChild(MatPaginator) paginator: MatPaginator;
  displayedColumns: string[] = ['address'];
  dataSource = new MatTableDataSource<DomainElement>();

  constructor(private dataService: DataService){
    this.suggestionList = []
    this.typedKeyword = ''
  }

  ngOnInit(): void {

  }


  ngAfterViewInit() {
   // this.dataSource.paginator = this.paginator;
  }

  searchkeyword($event){
    console.log($event.target.value);
    this.dataService.getSearchRelatedData($event.target.value).subscribe(response =>{
      if(response.status==200){
        let data = response['data']
        if(undefined != data['dataList']){
          this.suggestionList = data['dataList']
        }
      }
    })
  }

  keyword = 'Title';

  data: any;
  errorMsg: string;
  isLoadingResult: boolean;
  
  getServerResponse(keyword) {

    this.isLoadingResult = true;

    this.dataService.getSearchRelatedData(keyword).subscribe(response =>{
      if(response.message=='OK'){
        let data = response['data']
        if(undefined != data['dataList']){
          this.suggestionList = data['dataList']
          this.data = data['dataList']
        }
      }else{
        this.data = [];
        this.errorMsg = 'Error';
      }
      this.isLoadingResult = false;
    })
    this.typedKeyword = keyword;
  }


  searchCleared() {
    console.log('searchCleared');
    this.data = [];
  }

  selectEvent(item) {
    // do something with selected item
    if('' != item){
      this.isLoadingResult = true;
      this.getData(item);
    }
   
  }

  searchItem(){
    if('' != this.typedKeyword){
      this.isLoadingResult = true
      this.getData(this.typedKeyword)
    }
  }

  getData(keyword){
    this.dataService.getSearchRelatedData(keyword).subscribe(response =>{
      if(response.message=='OK'){
        let data = response['data']
        if(undefined != data['dataList']){
          this.dataSource.data =[]
          let list = data['dataList']
          let position = 0
          list.forEach(element => {

            this.dataSource.data.push({'position': position, 'address': element}) ; 
          });
          this.dataSource.paginator = this.paginator;
        }
      }else{
       
      }
      this.isLoadingResult = false;
    })
  }

  onChangeSearch(val: string) {
    // fetch remote data from here
    // And reassign the 'data' which is binded to 'data' property.
    this.typedKeyword = val
  }

  onFocused(e) {
    // do something when input is focused
  }

  shouldHide(){
    if(this.dataSource.data.length <=0){
      return true;
    }
    return false;
  }
}

export interface DomainElement {
  address: string;
  position: number;
}
