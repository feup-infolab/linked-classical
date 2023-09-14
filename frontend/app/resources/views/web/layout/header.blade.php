
@section('styles')
<link rel="stylesheet" href="https://unpkg.com/flowbite@1.4.5/dist/flowbite.min.css" />
@endsection

<nav class="w-100 bg-gray-light flex flex-row justify-between align-center">
    <div class="p-3 px-6 text-4xl">
        <a href="{{env('APP_URL')}}"
        class="hover:text-gray-dark">
            Linked Classical
        </a>
    </div>
    <div class="flex flex-row align-center items-center text-base">
        <div class="flex flex-row align-center items-center">
            <a href="{{env('APP_URL')}}"
                class="py-6 px-4 hover:text-gray-dark">
                Search
            </a>
        </div>
        <button id="dropdownDefault" data-dropdown-toggle="dropdown"
                class="py-6 px-4 hover:text-gray-dark text-center inline-flex items-center "
                type="button">Create
          <svg class="w-4 h-4 ml-2" fill="none" stroke="currentColor"
               viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 9l-7 7-7-7"></path>
          </svg>
        </button>
        <div id="dropdown"
             class="z-10 hidden bg-white divide-y divide-gray-100 rounded shadow w-44 dark:bg-gray-700">
          <ul class="py-6 text-sm text-gray-700 dark:text-gray-200" aria-labelledby="dropdownDefault">
            <li>
              <a href="{{ env('APP_URL') . '/composer/create' }}"
                 class="block px-4 pb-2 hover:text-gray-dark">Composer</a>
            </li>
            <li>
              <a href="{{ env('APP_URL') . '/musicalWork/create' }}"
                 class="block px-4 py-2 hover:text-gray-dark">Musical Work</a>
            </li>
          </ul>
        </div>
    </div>
</nav>
<script src="https://unpkg.com/flowbite@1.4.5/dist/flowbite.js"></script>
