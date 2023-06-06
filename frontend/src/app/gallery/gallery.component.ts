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
  tags: string[] = [];
  selectedTags: string[] = [];
  tagCtrl = new FormControl();
  private searchTerms = new Subject<string>();
  constructor(private http: HttpClient, private route: ActivatedRoute, private router: Router) {}
  ngOnInit(): void {
    this.getAllImages();
    this.getAllTags();
  }
  
  tagSearch = new Observable((text$: Observer<string | undefined>) =>{
    text$.next(this.tagCtrl.value);
  }).pipe(
    debounceTime(200),
    distinctUntilChanged(),
    switchMap((term: string) => this.searchTags(term))
  );

  getAllTags() {
    this.http.get('http://localhost:8080/tags').subscribe((tagObjects: any) => {
      tagObjects.map((tag: { tagName: string; }) => this.tags.push(tag.tagName));
    })
  }
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
    }
  }
  onClick(image: any) {
    this.router.navigate(['/image', image.image.id]);
  }
  getAllImages() {
    this.http
      .get('http://localhost:8080/images')
      .subscribe((response: any) => {
        
        this.allImages = [...response]
        this.route.queryParams.subscribe(params => {
          this.searchQuery = params['tags'] || '';
          this.filterGalleryItems();
        });
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
      this.selectedTags = this.searchQuery.split(',').map(tag => tag.trim().toLowerCase());
      console.dir(this.selectedTags);
      this.filteredGalleryItems = this.allImages.filter(item => {
        return this.selectedTags.every(tag => item.imageTagsList.some((itemTag:any) => itemTag.tagName.toLowerCase().includes(tag)));
      });
    }
  }
}
