import { Component, Input, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { FormControl } from '@angular/forms';
import { Observable, Observer, of, Subject } from 'rxjs';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-gallery',
  templateUrl: './gallery.component.html',
  styleUrls: ['./gallery.component.scss'],
})

export class GalleryComponent implements OnInit {
  allImages: any[] = [];
  searchQuery: string = '';
  filteredGalleryItems: any[] = [];
  tags: string[] = ["cat", "animal", "cute", "kitten", "photo"];
  selectedTags: string[] = [];
  tagCtrl = new FormControl();
  private searchTerms = new Subject<string>();
  constructor(private http: HttpClient, private route: ActivatedRoute, private router: Router) {}
  ngOnInit(): void {
    this.getAllImages()
   
    this.route.queryParams.subscribe(params => {
      this.searchQuery = params['tags'] || '';
      this.filterGalleryItems();
    });

  }
  
  tagSearch = new Observable((text$: Observer<string | undefined>) =>{
    text$.next(this.tagCtrl.value);
  }).pipe(
    debounceTime(200),
    distinctUntilChanged(),
    switchMap((term: string) => this.searchTags(term))
  );


  searchTags(term: string): Observable<string[]> {
    const matchingTags = this.tags.filter((tag) =>
      tag.toLowerCase().includes(term.toLowerCase())
    );
    return of(matchingTags);
  }

  onTagSelect(tag: string): void {
    this.addTag();
  }

  addTag(): void {
    const tagValue = this.tagCtrl.value.trim();
    if (tagValue && !this.selectedTags.includes(tagValue)) {
      this.selectedTags.push(tagValue);
      this.router.navigate([], {
        relativeTo: this.route,
        queryParams: { tags: this.selectedTags.join(',') },
        queryParamsHandling: 'merge'
      });
      this.filterGalleryItems();
    }
    this.tagCtrl.reset();
  }

  removeTag(tag: string): void {
    const index = this.selectedTags.indexOf(tag);
    if (index !== -1) {
      this.selectedTags.splice(index, 1);
      this.router.navigate([], {
        relativeTo: this.route,
        queryParams: { tags: this.selectedTags.join(',') },
        queryParamsHandling: 'merge'
      });
      this.filterGalleryItems();
    }
  }
  onClick(image: any) {
    this.router.navigate(['/image', image.id]);
  }
  getAllImages() {
    this.http
      .get('http://localhost:8080/images')
      .subscribe((response) => {
        console.dir(response);
        this.allImages.concat(response);
      });
  }

  onSearch() {
    this.router.navigate([], {
      relativeTo: this.route,
      queryParams: { tags: this.searchQuery },
      queryParamsHandling: 'merge'
    });
    this.filterGalleryItems();
  }

  filterGalleryItems() {
    if (!this.selectedTags.length && !this.searchQuery) {
      this.filteredGalleryItems = this.allImages;
    } else {
      // const tags = this.searchQuery.split(',').map(tag => tag.trim().toLowerCase());
      this.filteredGalleryItems = this.allImages.filter(item => {
        return this.selectedTags.every(tag => item.tags.some((itemTag:any) => itemTag.name.toLowerCase().includes(tag)));
      });
    }
  }
}


// this.allImages = [
//   {
//     id:1,
//     url: 'https://hips.hearstapps.com/hmg-prod/images/cute-cat-photos-1593441022.jpg?crop=1.00xw:0.753xh;0,0.153xh&resize=1200:*',
//     tags: [
//       { name: 'cat', confidence: 0.9 },
//       { name: 'animal', confidence: 0.8 },
//       { name: 'cute', confidence: 0.7 },
//     ],
//     analyzedOn: Date.now(),
//   },
//   {
//     id:2,
//     url: 'https://hips.hearstapps.com/hmg-prod/images/cute-cat-photos-1593441022.jpg?crop=1.00xw:0.753xh;0,0.153xh&resize=1200:*',
//     tags: [
//       { name: 'animal', confidence: 0.8 },

//     ],
//     analyzedOn: Date.now(),
//   },
//   {
//     id:3,
//     url: 'https://hips.hearstapps.com/hmg-prod/images/cute-cat-photos-1593441022.jpg?crop=1.00xw:0.753xh;0,0.153xh&resize=1200:*',
//     tags: [
//       { name: 'cat', confidence: 0.9 },

//     ],
//     analyzedOn: Date.now(),
//   },
//   {
//     id:4,
//     url: 'https://hips.hearstapps.com/hmg-prod/images/cute-cat-photos-1593441022.jpg?crop=1.00xw:0.753xh;0,0.153xh&resize=1200:*',
//     tags: [
//       { name: 'cute', confidence: 0.7 },
//     ],
//     analyzedOn: Date.now(),
//   },
// ];
