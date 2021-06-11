import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ExploreNistagramComponent } from './exploreNistagram.component';

import { ProfilKorisnikaComponent } from './profil-korisnika.component';

describe('ExploreNistagramComponent', () => {
  let component: ExploreNistagramComponent;
  let fixture: ComponentFixture<ExploreNistagramComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExploreNistagramComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExploreNistagramComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
