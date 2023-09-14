@extends('web.layout')

@section('title')
<title>Linked Classical</title>
@endsection

@section('scripts')
<script src="{{ asset('js/search.js') }}" defer></script>
@endsection

@section('styles')
<style>
  input:focus {
    outline: 0;
    background-color: aliceblue;
  }

  .autocomplete {
    position: relative;
    display: inline-block;
  }

  .autocomplete-items {
    position: absolute;
    border: 1px solid lightgray;
    border-top: none;
    border-bottom: 1px solid lightgray;
    z-index: 99;
    top: 100%;
    left: 0;
    right: 0;
  }

  .autocomplete-item {
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
  }

  .autocomplete-items div {
    padding: 0.5em;
    cursor: pointer;
    background-color: white;
  }

  .autocomplete-items div:hover {
    background-color: lightgray;
  }

  .autocomplete-active {
    background-color: gray !important;
    color: white;
  }
</style>
@endsection


@section('main')
<section class="mx-6 mx-auto py-6 xl:px-4 max-w-screen-md">
  <!--Header-->
  <div class="my-12 py-12 text-5xl text-center">
    Linked Classical
  </div>

  <!--Search Bar-->
  <form autocomplete="off" class="px-5 w-full" action="{{ env('APP_URL') }}/search" method="get">
    <div class="relative mx-2">
      <input id="searchInput" name="q" class="px-1 py-2 w-full border-b-2 text-base hover:text-gray-dark" type="text" placeholder="Search for composers, conductors, musical works and more">
      <button class="absolute top-2 right-2 hover:text-gray-dark" type="submit">
        @svg('heroicon-s-search', 'w-7 h-7')
      </button>
    </div>
  </form>

</section>
@endsection
