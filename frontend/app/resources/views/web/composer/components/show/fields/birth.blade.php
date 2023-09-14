<div class="relative mx-2">
  <span class="text-lg font-semibold">Birth</span>
  <button class="collapse_button absolute top-0 right-0 transition-transform duration-400 transform hover:text-gray-dark">
    @svg('heroicon-s-chevron-up', 'h-7 w-7')
  </button>
  <div class="relative overflow-hidden transition-max-h ease-out duration-300">
    <ul class="mt-4 text-base">
      @if($birth != null)
        <li class="mb-4 flex flex-row justify-start">
          <div class="w-2/6">Birth</div>
          <div class="w-4/6 font-thin">
            <a href="{{ env('APP_URL') . '/resource/' . $birth["birthUuid"] }}" class="underline">
              {{ $birth["birthURI"] }}
            </a>
          </div>
        </li>
        <li class="mb-4 flex flex-row justify-start">
          <div class="w-2/6">Date</div>
          <div class="w-4/6 font-thin">
            {{ $birth["date"] }}
          </div>
        </li>
      @else
        <li class="mb-4 flex flex-row justify-start">
          <div class="w-2/6">Birth</div>
          <div class="w-4/6 font-thin text-gray">No data available</div>
        </li>
      @endif
    </ul>

  </div>
</div>
